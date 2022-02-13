IF "%~1" == "" goto :EOF 
REG ADD "HKCU\Software\Microsoft\Windows NT\CurrentVersion\AppCompatFlags\Layers" /v "%~1" /t REG_SZ /d "~ HIGHDPIAWARE" /f