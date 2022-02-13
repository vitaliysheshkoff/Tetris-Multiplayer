set "params=%*"
cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && %~s0 %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )
reg add HKEY_CLASSES_ROOT\tetris /ve /d "URL:tetris Protocol" /f
reg add HKEY_CLASSES_ROOT\tetris /v "URL Protocol" /f
reg add HKEY_CLASSES_ROOT\tetris\shell /ve /f
reg add HKEY_CLASSES_ROOT\tetris\shell\open /ve /f
reg add HKEY_CLASSES_ROOT\tetris\shell\open\command /ve /d "%1 %%1" /f
