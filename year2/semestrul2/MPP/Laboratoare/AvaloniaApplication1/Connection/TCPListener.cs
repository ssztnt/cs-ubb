using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;

class Server
{
    private TcpListener _listener;

    public void StartServer()
    {
        _listener = new TcpListener(IPAddress.Any, 5000);
        _listener.Start();
        Console.WriteLine("Server started...");

        while (true)
        {
            var client = _listener.AcceptTcpClient();
            Console.WriteLine("Client connected...");
            Thread clientThread = new Thread(() => HandleClient(client));
            clientThread.Start();
        }
    }

    private void HandleClient(TcpClient client)
    {
        var stream = client.GetStream();
        byte[] data = System.Text.Encoding.UTF8.GetBytes("Hello, Client!");
        stream.Write(data, 0, data.Length);

        client.Close();
    }
}