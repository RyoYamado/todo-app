param([string[]]$GradleTasks = @('clean', 'build'))
$ErrorActionPreference = 'Stop'
$root = $PSScriptRoot
$drive = $null
foreach ($letter in [char[]]'RSTUVWXYZ') {
    $candidate = "${letter}:"
    if (-not (Test-Path "$candidate\")) {
        & subst.exe $candidate $root
        if ($LASTEXITCODE -eq 0) {
            $drive = $candidate
            break
        }
    }
}
if (-not $drive) {
    throw 'No available temporary drive for Gradle.'
}
$exitCode = 1
Push-Location "$drive\"
try {
    & .\gradlew.bat --no-daemon @GradleTasks
    $exitCode = $LASTEXITCODE
} finally {
    Pop-Location
    & subst.exe $drive /D
}
exit $exitCode
