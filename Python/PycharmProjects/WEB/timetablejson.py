import json
import datetime
import time
import calendar
import sys

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
    "Thursday": {
      "MPET": {}
    },
    "Tuesday": {
      "AGPL": {}
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
      "COMPILERT": {}
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
  },
  "02": {
    "Thursday": {
      "OOT": {}
    }
  }
}'''


def subject():
    global subjectname
    currenttime = time.strftime("%H", time.localtime())
    print(currenttime)
    day2 = str(datetime.date.today()).split('-')
    day1 = calendar.day_name[datetime.datetime.strptime(day2[2] + " " + day2[1] + " " + day2[0], '%d %m %Y').weekday()]
    print(day1)
    store = json.loads(person)
    try:
        subjectname = (str(store[currenttime][day1]).strip("{").split(':'))
        subjectname = subjectname[0].strip("'")
        if subjectname.endswith('T'):
            print(subjectname)
            print("its a theory class of 1 hr")
            return subjectname
        else:
            print(subjectname)
            print("its a lab of 2 hr")
            return subjectname
    except:
        return "you are free"


def wait(timewaitother):
    global timewait
    subjectname = subject()
    if subjectname.endswith("T"):
        timewait = 3600
    elif subjectname.endswith("L"):
        timewait = 7200
    else:
        timewait = 3600
    print(f'{timewait} {int(timewaitother) * 60}')
    t = timewait - int(timewaitother) * 60
    for i in range(t, 0, -1):
        # print("running"
        mins, secs = divmod(t, 60)
        timeformat = '{:02d}:{:02d}'.format(mins, secs)
        print('\r'+ timeformat, end='')
        time.sleep(1)
        t-=1

    print("\n")