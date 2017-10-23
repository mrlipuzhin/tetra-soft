# tetra-soft
test

Перед запуском приложения необходимо создать конфигурационный файл `$HOME/.java/.userPrefs/tetrasoft/prefs.xml`.

Описание настроек.
`data_dir` - директория, где располагаются файлы с треугольниками.
`output_dir` - директория, где будут создаваться файлы с результатами работы программы.
`enable_logging` - флаг для включение/отключение логирования в sysout.

Пример заполненного файла:
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE map SYSTEM "http://java.sun.com/dtd/preferences.dtd">
<map MAP_XML_VERSION="1.0">
<entry key="output_dir" value="/home/mrlipuzhin/Desktop/out"/>
<entry key="data_dir" value="/home/mrlipuzhin/Desktop/files"/>
<entry key="enable_logging" value="false"/>
</map>
```
