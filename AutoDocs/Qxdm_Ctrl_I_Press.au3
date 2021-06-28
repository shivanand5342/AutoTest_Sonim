#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Outfile_x64=D:\AutoApp\Automation\AutoDocs\QXDM_Ctrl_I_Press.exe
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
WinActivate("[CLASS:Qt5QWindowIcon]")
Sleep(2000)
WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MAXIMIZE)
Sleep(5000)
Send("^i")
if WinWaitActive("Save Item Store (Cancel To Discard)?", "" , 5) then
	;WinWaitActive("Save Item Store (Cancel To Discard)?", "" , 10)
	ControlFocus("Save Item Store (Cancel To Discard)?","","Edit1")
	ControlSetText("[CLASS:Edit]","","Edit1",$CmdLine[1])
	;ControlSetText("Save Item Store (Cancel To Discard)?","","Edit1","D:\jar\aa.isf")
	sleep(2000)
	;ControlClick("Save Item Store (Cancel To Discard)?","","Button2")
	Send("{ENTER}")
EndIf
sleep(2000)
WinSetState("[CLASS:Qt5QWindowIcon]","",@SW_MINIMIZE)
