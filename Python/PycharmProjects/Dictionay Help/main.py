# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import json
import sqlite3
from time import sleep

import requests


def create_connection(db_file):
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Exception as e:
        print(e)

    return conn


# https://api.dictionaryapi.dev/api/v2/entries/en/hello
def writeInfile(jon, f):
    str = jon[0]['word']
    f.write(json.dumps(str, indent=4) + ",[{")
    try:
        f.write(json.dumps("phoentic") + ":" + json.dumps(jon[0]['phonetic'], indent=4, ensure_ascii=False) + ",")
    except KeyError as e:
        f.write(json.dumps("phoentic") + ":" + "{},")

    for i in range(len(jon[0]['meanings'])):
        f.write("\"" + json.dumps(i + 1) + "\"" + ":" + "{")
        try:
            f.write(json.dumps("partOfSpeech") + ":" + json.dumps(
            jon[0]['meanings'][i]['partOfSpeech']) + ", \"defintions\" : [ ")
        except KeyError:
            f.write(json.dumps("partOfSpeech") + ":" + json.dumps("phrase") + ", \"defintions\" : [ ")

        for j in range(len(jon[0]['meanings'][i]['definitions'])):
            f.write("{" + json.dumps("definition") + ":" + json.dumps(
                jon[0]['meanings'][i]['definitions'][j]['definition']) + "},")
        f.write("]},")
    f.write("}],")



def select_all_data(conn):
    cur = conn.cursor()
    cur.execute("SELECT word"
                " FROM Word")

    rows = cur.fetchall()
    i = 0
    f = open("words.json", "a", encoding='utf-8')
    f.write("[" )
    for row in rows:
        str = ''.join(row)
        print("\r",str,end="")
        try:
            response = requests.get("https://api.dictionaryapi.dev/api/v2/entries/en/" + str)
            data = response.json()
        except Exception as e:
            print("error occured " + e.__str__())
            f1=open("error.txt","a")
            f1.write( str + "\n" )
            f1.close()
            continue
        try:
            writeInfile(data, f)
        except Exception as e:
            f1 = open("error.txt", "a")
            f1.write(str + "\n")
            f1.close()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    conn = create_connection("C:\AllProjects\Python\PycharmProjects\Dictionay Help\WordsList.db")
    select_all_data(conn)
