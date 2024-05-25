package com.ssd.app;
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
        System.out.println(Transaction.getAllFields());
        });
        System.out.println("\n" + "\n");
    }


    private void sendBindTransaction(int auctionid, int amount){
        System.out.println("\nSending bid\n");
        Transaction temp = new Transaction("bid", auctionid, amount, userid);
        //temp.signTransaction(keypair);
        TransactionApp transaction = AuctionUtil.convertTransactiontoTransactionAPP(temp);
        Ack ack;
        //ack = blockingStub.submitTransaction(temp);
        //System.out.println(ack.getAcknowledge());
    }

    public static void main(String[] args) {
        KeyPairs keypair = new KeyPairs();
        App app = new App("localhost", 5000, 1, keypair);
        Scanner s = new Scanner(System.in);
        int choice;
        while(true){
            printMenu();
            choice = s.nextInt();
            switch(choice){
                case 1:
                    app.listAuctions();
                    break;
                case 2:
                    System.out.println("Select auction to bid\n");
                    int auctionid = s.nextInt();
                    System.out.println("Select amount to bid\n");
                    int amount = s.nextInt();
                    app.sendBindTransaction(auctionid, amount);
                    break;
                case 3:
                    //app.sendTransaction(choice);
                    break;
                case 4:
                    //app.sendTransaction(choice);
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
