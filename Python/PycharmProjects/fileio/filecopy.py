import shutil
import os

shutil.copytree(os.path.expandvars("%localappdata%\\Google\Chrome"),

              "D:\Temp",ignore=shutil.ignore_patterns('Default'))
