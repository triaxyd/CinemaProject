import mysql.connector
import bcrypt
from random import *
from secrets import choice
from string import * 
from datetime import datetime
from mysql.connector import Error
from faker import Faker



def genPassword():
    length = randint(8,20)
    alphabet = ascii_letters + digits + punctuation
    requirements = [ascii_uppercase, ascii_lowercase, digits, punctuation, *(length-4)*[alphabet]]
    return "".join(choice(req) for req in sample(requirements,length))

def getSalt():
    return bcrypt.gensalt()

def hashPassword(password,salt):
    return bcrypt.hashpw(password.encode('utf8'),salt)



def createUser():
    first_name = f.first_name()
    last_name = f.last_name()

    name = first_name + " " + last_name
    username = first_name.lower() + last_name.lower()
    email = username + "@example.com"

    password = genPassword()
    salt = getSalt()
    hashedPassword = hashPassword(password,salt)

    create_time = (f.date_between_dates(date_start=datetime(2023,1,1), date_end=datetime(2023,5,28))).strftime("%Y-%m-%d")
    role = "Customer"

    return username,name,email,hashedPassword,create_time,role,salt




def insertToUser(u,e,hp,c_t,r,s):
    valuesUser = (u,e,hp,c_t,r,s)
    sqlUser = "INSERT INTO user (username,email,password,create_time,role,salt) VALUES (%s,%s,%s,%s,%s,%s)"
    cursor.execute (sqlUser,valuesUser)

def insertToCustomer(n,u):
    valuesCustomer = (n,u)
    sqlCustomer = "INSERT INTO customers(NAME,user_username) VALUES (%s,%s)"
    cursor.execute (sqlCustomer,valuesCustomer)




f = Faker()
try:
    connection = mysql.connector.connect(host = 'localhost',
                                         database ='javakratiseis',
                                         user='root',
                                         password='root')
    if connection.is_connected():
        #db_info = connection.get_server_info()
        print("connected")

        cursor = connection.cursor()

        inputTimes = int(input("How many users to enter: "))
        
        for i in range(inputTimes):

            username, name, email, hashedPassword, create_time, role,salt = createUser()

            insertToUser(username,email,hashedPassword,create_time,role,salt)
            print(username + " inserted in user")

            insertToCustomer(name,username)
            print(username + " inserted in customer")

        connection.commit()
        print("Insert successful")

        
except Error as e:
    print("Failed to  connect ",e)

