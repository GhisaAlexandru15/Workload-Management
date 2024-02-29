#!/bin/bash
javac *.java

#stergere fisiere existente
cd Statistici && find . -maxdepth 1 -type f ! -name "*.sh" ! -name "*.java" -exec rm -v {} \; && cd ..

#program java
ziua0=$(date -d "2024-01-21" +'%Y%m%d')
azi=$(date +'%Y%m%d')
saptamana=1
while [ "$ziua0" -lt "$azi" ]
do
    dataf=$(date -d "$ziua0" +'%d-%m-%Y')
    java WorkloadManagement "$dataf" "$saptamana" "false"
    ziua0=$(date -d "$ziua0 + 7 days" +'%Y%m%d')
    saptamana=$(($saptamana + 1))    
done
dataf=$(date -d "$azi" +'%d-%m-%Y')
java WorkloadManagement "$dataf" "$saptamana" "true"
rm *.class

#creare excel
cd Statistici
libreoffice --headless --convert-to xlsx:"Calc MS Excel 2007 XML" --infilter="CSV:44,34,UTF-8" *.csv
ssconvert --merge-to="Statistici $(date +"%d-%m-%y")".xlsx *.xlsx
find . -maxdepth 1 -type f ! -name "Statistici*" ! -name "*.sh" ! -name "*.java" -exec rm -v {} \;
cd ..

