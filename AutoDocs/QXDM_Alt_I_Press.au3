#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Outfile_x64=QXDM_Alt_I_Press.exe
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
WinActivate("[CLASS:Qt5QWindowIcon]")
Sleep(2000)
WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MAXIMIZE)
Sleep(2000)
Send("!i")
sleep(2000)
WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MINIMIZE)