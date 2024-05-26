package com.ssd.app;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Scanner;

import com.ssd.blockchain.KeyPairs;
import com.ssd.blockchain.Transaction;
import com.ssd.grpc.Ack;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.AuctionGrpc.AuctionBlockingStub;
import com.ssd.grpc.AuctionGrpc.AuctionStub;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.Id;
import com.ssd.util.AuctionUtil;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class App {
    final int userid;
    final Id id;
    final AuctionBlockingStub blockingStub;
    final AuctionStub asyncStub;
    final ManagedChannel channel;
    //final KeyPairs keypair;

    public App(String host, int port, int id, KeyPairs keypair) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = AuctionGrpc.newBlockingStub(channel);
        asyncStub = AuctionGrpc.newStub(channel);
        this.id = Id.newBuilder().setId(id).build();
        this.userid = id;
        //this.keypair = keypair;
    } 

    private static void printMenu(){
        System.out.println("-----Auction APP, insert the number for the operation you would like to do-----\n\n" +
            "1) List auctions\n" + "2) Bid\n" + "3) Start auction\n" + "4) End auction\n" + "5) Exit app\n");
    }

    private void listAuctions() {
        System.out.println("Auctions ativas:\n");
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.listAuctions(id).forEachRemaining(Transaction -> {
        System.out.println("Auction id : " + Transaction.getAuctionId() + " -> Item: " + Transaction.getItem());
        });
        System.out.println("\n" + "\n");
    }


    private void sendBindTransaction(int auctionid, int amount){
        System.out.println("\nSending bid\n");
        Transaction temp = new Transaction("bid", auctionid, amount, userid);
        //temp.signTransaction(keypair);
        TransactionApp transaction = AuctionUtil.convertTransactiontoTransactionAPP(temp);
        System.out.println(temp);
        Ack ack;
        ack = blockingStub.submitTransaction(transaction);
        System.out.println(ack.getAcknowledge() + "\n");
    }

    private void sendAuctionStartTransaction(String item){
        System.out.println("\nStarting auction of item " + item + "\n");
        int auctionid = -1;
        Transaction temp = new Transaction("start_auction", userid, auctionid, item);
        TransactionApp transaction = AuctionUtil.convertTransactiontoTransactionAPP(temp);
        System.out.println(transaction.getItem());
        Ack ack;
        ack = blockingStub.submitTransaction(transaction);
        System.out.println(ack.getAcknowledge() + "\n");

    }

    private void sendEndAuctionTransaction(int auctionid){
        System.out.println("\nEnding auction" + auctionid + "\n");
        Transaction temp = new Transaction("end_auction", userid, auctionid);
        TransactionApp transaction = AuctionUtil.convertTransactiontoTransactionAPP(temp);
        Ack ack;
        ack = blockingStub.submitTransaction(transaction);
        System.out.println(ack.getAcknowledge() + "\n");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 3) {
            System.err.println("Usage: java App <ip_address> <port> <client_id>");
            System.exit(1);
        }
        int id = Integer.parseInt(args[2]);
        String ipAddress = args[0];
        int port = Integer.parseInt(args[1]);

        KeyPairs keypair = new KeyPairs();
        App app = new App(ipAddress, port, id, keypair);
        Scanner s = new Scanner(System.in);
        int auctionid = 0;
        int amount =0;
        int choice;
        String item = "";
        int auction = 0;
        while(true){
            printMenu();
            choice = s.nextInt();
            s.nextLine();
            switch(choice){
                case 1:
                    app.listAuctions();
                    break;
                case 2:
                    System.out.println("Select auction to bid\n");
                    auctionid = s.nextInt();
                    s.nextLine();
                    System.out.println("Select amount to bid\n");
                    amount = s.nextInt();
                    s.nextLine();
                    app.sendBindTransaction(auctionid, amount);
                    break;
                case 3:
                    System.out.println("What item to auction?\n");
                    item = s.nextLine();
                    app.sendAuctionStartTransaction(item);
                    break;
                case 4:
                    System.out.println("Select auction to end\n");
                    auction = s.nextInt();
                    s.nextLine();
                    app.sendEndAuctionTransaction(auction);
                    break;
                case 5:
                    System.out.println("\nExiting client\n");
                    System.exit(0);
                default:
                    System.out.println("\nOption not supported" + "\n");
                    break;
            } 
        }
    }
}
