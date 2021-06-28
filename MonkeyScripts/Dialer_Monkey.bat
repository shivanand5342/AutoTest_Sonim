@set Folder="All_%START%" 
cd ..\Output
@mkdir %Folder%
cd %Folder%

@echo Monkey started: %START%

@echo Dumping logs
adb logcat -c
start "log_window" /MIN cmd /c "adb logcat -v threadtime -b all>>DIALER_MONKEY.txt 

@adb shell monkey -p com.google.android.dialer --throttle 1000 -s 1000 --ignore-crashes --ignore-timeouts --kill-process-after-error --ignore-security-exceptions -v 22000>>DIALER_monkeyeventlog.txt

taskkill /IM cmd.exe /FI "WINDOWTITLE eq log_window"
pause