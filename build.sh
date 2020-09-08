set -e

rm -rf build
cp -r src build

find build | grep '\.i$' | while read line; do
    cpp -P "$line" > "${line:0:${#line}-1}"java
done

find build | grep '\.java$' | while read line; do
    if [ ! -e "${line:0:${#line}-4}"class ]; then
        echo javac -cp build "$line"
        javac -cp build "$line"
    fi
done

mkdir -p build/META-INF
cp MANIFEST.MF build/META-INF
( cd build; python3 -m zipfile -c ../launcher.jar .; )
rm -rf build
