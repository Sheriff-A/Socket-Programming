"""
Server Class
"""
import socket
# localhost, free port (> 1023)
HOST, PORT = '127.0.0.1', 1234

# Automatically closes the socket at the end of successful run
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as ss:
    print('Server Socket Created!')

    # IPv4 requires a 2 argument tuple (HOST, PORT)
    # The HOST should be a numeric address
    # The server listens to connections on that HOST address coming through the specific PORT
    # If the HOST is blank, all connections on IPv4 are accepted
    ss.bind((HOST, PORT))

    # Set the server to listen for a connection
    ss.listen()

    # accept() blocks and waits for an in coming connection
    # When a client connects. A socket object is returned, as well as an address tuple
    # conn = *socket object*
    # addr = (HOST, PORT) for IPv4
    conn, addr = ss.accept()

    # Automatically closes the socket at the end of successful run
    with conn:
        print('Connected by', addr)
        while True:

            # Receives byte stream from socket object
            # .recv(byte-size)
            data = conn.recv(1024)

            # if empty string is received, close
            if not data:
                break

            # Echo the connected socket's message back to it
            conn.sendall(data)
