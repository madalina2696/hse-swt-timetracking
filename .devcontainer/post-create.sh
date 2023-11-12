sudo apt update -q
sudo apt upgrade -y -q
sudo apt-get install libx11-dev -y
sudo apt install maven -y
export PATH_TO_FX=/workspaces/hse-swt-timetracking/libs/javafx-sdk-21.0.1/lib
sudo apt-get install -y xvfb
sudo apt-get install -y libgtk-3-0 libglu1-mesa libxi6 libgconf-2-4
sudo chmod 1777 /tmp/.X11-unix
Xvfb :99 &
export DISPLAY=:99