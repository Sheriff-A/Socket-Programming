"""
Client Class
"""

import socket
import json

# localhost, free port (> 1023)
HOST, PORT = '127.0.0.1', 1234

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall(str.encode('Hello, World'))
    data1 = s.recv(1024)
    orig_list = [1, 2, 3]
    l_string = json.dumps(orig_list)
    s.sendall(str.encode(l_string))
    data2 = s.recv(1024)
    l_list = json.loads(data2)


print('Received:', repr(data1))
print('Received:', repr(data2))
print('orig_list:', orig_list)
print('orig_list type:', type(orig_list))
print('l_string:', l_string)
print('l_string type:', type(l_string))
print('l_list:', l_list)
print('l_list type:', type(l_list))
