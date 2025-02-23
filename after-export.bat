@echo off

REM SPDX-FileCopyrightText: 2025 Wong Yi Xiong <https://github.com/TheRealMintd>
REM
REM SPDX-License-Identifier: MPL-2.0

set ARGS=%*
set SCRIPT=%~dp0after-export.clj
bb -f %SCRIPT% %ARGS%
