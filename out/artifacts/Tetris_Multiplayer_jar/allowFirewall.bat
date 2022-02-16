echo on
for /F "delims=" %L in ('nul 2>&1 java -XshowSettings:properties -version | findstr "java\.home"') do (set "VAR=%L")
pause