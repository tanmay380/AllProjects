import time

import requests
from selenium import webdriver
from selenium.webdriver.common.alert import Alert

driver=webdriver.Chrome("D:\Tanmay Singhal\Downloads\chromedriver.exe")
driver.get("https://learn.upes.ac.in/")
driver.find_element_by_id("user_id").send_keys("500069450@stu.upes.ac.in")
driver.find_element_by_id("password").send_keys("tanmay427462")
driver.find_element_by_id("entry-login").click()
time.sleep(2)
driver.find_element_by_class_name("button-1").click()
time.sleep(1)
# driver.find_element_by_link_text("Advanced Game Programming Algorithms.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_312").click()
# time.sleep(1)
# driver.find_element_by_id("menuPuller").click()
# time.sleep(1)
# driver.find_element_by_partial_link_text("Collaborate").click()
# time.sleep(9)
driver.get("https://learn.upes.ac.in/webapps/blackboard/content/contentWrapper.jsp?cours"
           "e_id=_45387_1&displayName=Blackboard%20Collaborate%20Ultra%20UPES-CCE&href=%2Fwebapps%2Fblackb"
           "oard%2Fexecute%2Fblti%2FlaunchPlacement%3Fblti_placement_id%3D_22_1%26course_id%3D_45387_1%26mode"
           "%3Dview%26wrapped%3Dtrue")


# driver.quit()
time.sleep(14)

driver.find_element_by_partial_link_text("Community").click()
