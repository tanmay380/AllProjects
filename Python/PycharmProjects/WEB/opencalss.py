# Simple assignment
from selenium.webdriver import Chrome

driver = Chrome()

# Or use the context manager
from selenium.webdriver import Chrome

with Chrome() as driver:
# your code inside this indent
