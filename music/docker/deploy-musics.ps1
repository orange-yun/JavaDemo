$WorkspaceDir = Resolve-Path -Path ..
$ManagementProjectDir = Join-Path $WorkspaceDir 'music-management'
$SubmissionProjectDir = Join-Path $WorkspaceDir 'music-publishing'
$GatewayProjectDir = Join-Path $WorkspaceDir 'music-gateway'

function Build-Project
{
  [CmdletBinding()]
  param (
    [Parameter(Mandatory)]
    [string] $Project,

    [Parameter()]
    [ValidateSet('maven')]
    [string] $Type = 'maven'
  )

  $ProjectName = Split-Path -Path $Project -Leaf
  Write-Host "building project $ProjectName ..."

  Set-Location $Project
  switch ($Type)
  {
    'maven' {
      if (Test-Path -Path .\target -PathType Container)
      {
        Remove-Item -Path .\target -Force -Recurse
      }

      try
      {
        mvn clean package -DskipTests
      }
      catch
      {
        Write-Host $Error
      }

      if (Test-Path -Path .\docker\app.jar)
      {
        Remove-Item -Path .\docker\app.jar
      }

      Move-Item -Path .\target\*.jar -Destination .\docker\app.jar
    }
    Default {
      throw 'invalid project type'
    }
  }

}

Build-Project -Project $ManagementProjectDir -Type 'maven'
Build-Project -Project $SubmissionProjectDir -Type 'maven'
Build-Project -Project $GatewayProjectDir -Type 'maven'

Set-Location "$WorkspaceDir/docker"

docker-compose up --build -d
