import time

import requests
from selenium import webdriver
options=webdriver.ChromeOptions()
options.add_argument("user-data-dir=D:\Temp\\User Data\default1")
driver=webdriver.Chrome(executable_path="D:\Tanmay Singhal\Downloads\chromedriver.exe",options=options)
driver.get("https://learn.upes.ac.in/")
driver.find_element_by_id("user_id").send_keys("500069450@stu.upes.ac.in")
driver.find_element_by_id("password").send_keys("tanmay427462")
driver.find_element_by_id("entry-login").click()
time.sleep(2)

def MicroProcessorTheory():
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Micro Processor & Embedded Systems.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_314").click()
    # time.sleep(1)
    # driver.find_element_by_id("menuPuller").click()
    # time.sleep(1)
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-b7262a1fb18a45f5a842a18d08552524").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()
def ObjectOrientedAnalysis():
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Object Oriented Analysis and Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_310").click()
    # time.sleep(1)
    # driver.find_element_by_id("menuPuller").click()
    # time.sleep(1)
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(9)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-1e93f5d8335b417e8a2d0f89000335a7").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


# driver.find_element_by_class_name("button-1").click()
# time.sleep(1)
MicroProcessorTheory()
more=input("Want to see more -> ")
if  more=='y':
    ObjectOrientedAnalysis()