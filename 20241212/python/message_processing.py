import contextlib
from typing import AsyncIterator
from client_connection import ClientConnection

async def read_messages(reader: asyncio.StreamReader, max_size: int = 1024) -> AsyncIterator[str]:
    async for data in reader:
        if not data:
            break

        message = data.decode("utf-8").strip()
        if message:
            yield message

async def handle_client_connection(connection: ClientConnection, manager: "ConnectionManager") -> None:
    with contextlib.suppress(asyncio.CancelledError, ConnectionResetError):
        async with contextlib.AsyncExitStack() as stack:
            await stack.enter_async_context(connection.reader)
            await stack.enter_async_context(connection.writer)

            async for message in read_messages(connection.reader):
                await manager.broadcast(message, connection.writer)