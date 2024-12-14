class ConnectionManager:
    async def broadcast(self, message: str, writer: asyncio.StreamWriter):
        print(f"Broadcasting: {message}")