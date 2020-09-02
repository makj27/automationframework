cd ./
docker pull elgalu/selenium
docker pull dosel/zalenium
docker run --rm -ti --name zalenium -p 4444:4444 ^
      -v /var/run/docker.sock:/var/run/docker.sock ^
      -v /c/Users/E01967/temp/videos:/home/seluser/videos ^
      --privileged dosel/zalenium start