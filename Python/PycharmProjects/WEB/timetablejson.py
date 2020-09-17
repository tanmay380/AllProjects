import json
import datetime
import time
import calendar

person = '''{
  "09": {
    "Monday": {
      "OOT": {}
    },
    "Tuesday": {
      "AGPT": {}
    },
    "Wednesday": {
      "WEBT": {}
    },
    "Friday": {
      "CompilerT": {}
    }
  },
  "10": {
    "Tuesday": {
      "CompilerT": {}
    },
    "Wednesday": {
      "OOT": {}
    },
    "Thursday": {
      "AGPT": {}
    },
    "Friday": {
      "AGPL": {}
    }
  },
  "11": {
    "Monday": {
      "WEBT": {}
    },
    "Tuesday": {
      "AGPL": {}
    },
    "Thursday": {
      "MPET": {}
    },
    "Wednesday": {
      "WEBL": {}
    },
    "Friday": {
      "OOADL": {}
    }
  },
  "13": {
    "Monday": {
      "BIZT": {}
    },
    "Thursday": {
      "MPEDL": {}
    }
  },
  "14": {
    "Tuesday": {
      "MPET": {}
    },
    "Wednesday": {
      "AGPT": {}
    },
    "Friday": {
      "BIZT": {}
    }
  },
  "16": {
    "Monday": {
      "OOT": {}
    },
    "Tuesday": {
      "CompilerT": {}
    },
    "Wednesday": {
      "AGPT": {}
    },
    "Thursday": {
      "WEBT": {}
    },
    "Friday": {
      "MPET": {}
    }
  }
}'''

day2 = str(datetime.date.today()).split('-')
day1 = calendar.day_name[datetime.datetime.strptime(day2[2] + " " + day2[1] + " " + day2[0], '%d %m %Y').weekday()]
print(day1)

def subject():
    global subjectname
    currenttime = time.strftime("%H", time.localtime())
    print(currenttime)
    store = json.loads(person)
    try:
        subjectname = (str(store[currenttime][day1]).strip("{").split(':'))
        subjectname = subjectname[0].strip("'")
        nextsubject = nextclass(currenttime, store)
        print(nextsubject)
        if subjectname.endswith('T'):
            print(subjectname)
            print("its a theory class of 1 hr")
            print(nextsubject)
            return subjectname
        else:
            print(subjectname)
            print("its a lab of 2 hr")
            print(nextsubject)
            return subjectname
    except:
        print("no class now")
        nextsubject = nextclass(currenttime, store)
        print(nextsubject)
        return "you are free"


def nextclass(currenttime, store):
    try:
        nextsubject = (str(store[str(int(currenttime) + 1)][day1]).strip("{").split(':'))
        nextsubject = "next class is of "+nextsubject[0].strip("'")
    except:
        nextsubject = f"NO class is at {int(currenttime) + 1}"
    return nextsubject


def wait(subjectname,timewaitother):
    global timewait
    # subjectname = subject()
    if subjectname.endswith("T"):
        timewait = 3600
    elif subjectname.endswith("L"):
        timewait = 7200
    else:
        timewait = 3600
    t = timewait - (int(timewaitother) * 60-1)
    for i in range(t, 0, -1):
        mins, secs = divmod(t, 60)
        timeformat = '{:02d}:{:02d}'.format(mins, secs)
        print('\r'+ timeformat, end='')
        time.sleep(1)
        t-=1
    # input("lcdc")
    print("\n")