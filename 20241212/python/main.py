from config import HOST, PORT
from message_processing import handle_client_connection
from connection_manager import ConnectionManager
from client_connection import ClientConnection

async def main():
    reader, writer = await asyncio.open_connection(HOST, PORT)
    connection = ClientConnection(reader, writer)
    manager = ConnectionManager()

    await handle_client_connection(connection, manager)

if __name__ == '__main__':
    asyncio.run(main())
