@ECHO OFF

REM ************************************
SET _proxy=-H proxy2ndtier.asbbank.co.nz -P 80
SET _distributed=
REM SET _distributed=-R10.133.136.17,10.133.138.160,10.133.140.8
SET _result_dir=%cd%\Results\
SET _result_csv=%_result_dir%API_Calculate_Premium.csv
SET _result_dashboard_dir=%_result_dir%Calculate_Premium\
IF EXIST %_result_dir% (ECHO Results Dir Exists.) ELSE (mkdir %_result_dir%)
IF EXIST %_result_csv% (del /S %_result_csv%)
IF EXIST %_result_dashboard_dir% (
    del /S /Q %_result_dashboard_dir%
    rmdir /S /Q %_result_dashboard_dir%)
SET _wait_time=3
REM ************************************

SET /P _users=Input Number of Threads (users)
ECHO Number of Threads: %_users%
SET /P _ramp=Input Ramp-Up Period (in seconds)
ECHO Ramp-Up Period: %_ramp%
SET /P _max_lives=Input Max Lives (Input 1 for single life)
ECHO Max Lives: %_max_lives%
SET /P _duration=Input Duration (in seconds)
ECHO Duration: %_duration%
SET /P _use_proxy=Use Proxy? Input Y or N
IF %_use_proxy%==N (
    SET _proxy=)
ECHO Use Proxy: %_proxy%
REM SET /P _run_distributed=Run Distributed Testing? Input Y or N
REM IF %_run_distributed%==N SET _distributed=
REM ECHO Run Test in Distributed Mode: %_run_distributed%
ECHO Testing Will Kick off in %_wait_time% Seconds...
TIMEOUT /t %_wait_time%
jmeter -Jusers=%_users% -Jramp=%_ramp% -Jduration=%_duration% -JmaxLives=%_max_lives% %_proxy% %_distributed% -n -t %cd%\API_Calculate_Premium_II.jmx -l %_result_csv% -e -o %_result_dashboard_dir%
