@echo off
SetLocal EnableDelayedExpansion

START /D "C:\Program Files (x86)\BWAPI\Chaoslauncher" Chaoslauncher.exe

:loop
TASKLIST | FIND /I /C "java.exe" | FINDSTR 1
if %errorlevel% == 1 (
    TASKKILL /F /IM StarCraft.exe
    TASKKILL /F /IM Chaoslauncher.exe
    TASKKILL /F /IM java.exe
    EXIT 0
)
goto loop
