import java.util.ArrayList;
import java.util.Date;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

class Item {
    private String itemName;
    private String itemDescription;
    private double startingPrice;

    public Item(String itemName, String itemDescription, double startingPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.startingPrice = startingPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getStartingPrice() {
        return startingPrice;
    }
}

class Bid {
    private User bidder;
    private double amount;

    public Bid(User bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public User getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }
}

class Auction {
    private Item item;
    private Date startTime;
    private Date endTime;
    private ArrayList<Bid> bids;

    public Auction(Item item, Date startTime, Date endTime) {
        this.item = item;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bids = new ArrayList<>();
    }

    public Item getItem() {
        return item;
    }

    public void placeBid(User bidder, double amount) {
        if (new Date().before(endTime)) {
            bids.add(new Bid(bidder, amount));
        } else {
            System.out.println("Auction ended.");
        }
    }

    public Bid getHighestBid() {
        Bid highestBid = null;
        for (Bid bid : bids) {
            if (highestBid == null || bid.getAmount() > highestBid.getAmount()) {
                highestBid = bid;
            }
        }
        return highestBid;
    }
}

class AuctionSystem {
    private ArrayList<User> users;
    private ArrayList<Auction> auctions;

    public AuctionSystem() {
        users = new ArrayList<>();
        auctions = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void createAuction(Auction auction) {
        auctions.add(auction);
    }

    public void listAuctions() {
        for (Auction auction : auctions) {
            System.out.println("Auction for item: " + auction.getItem().getItemName());
        }
    }

    public void viewAuctionDetails(Auction auction) {
        System.out.println("Item: " + auction.getItem().getItemName());
        System.out.println("Description: " + auction.getItem().getItemDescription());
        System.out.println("Starting Price: " + auction.getItem().getStartingPrice());
        Bid highestBid = auction.getHighestBid();
        if (highestBid != null) {
            System.out.println("Highest Bid: " + highestBid.getAmount());
            System.out.println("Bidder: " + highestBid.getBidder().getUsername());
        } else {
            System.out.println("No bids yet.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AuctionSystem system = new AuctionSystem();

        User seller = new User("seller", "password");
        system.registerUser(seller);

        Item item = new Item("Laptop", "A high-end gaming laptop", 500.0);
        Auction auction = new Auction(item, new Date(), new Date(System.currentTimeMillis() + 3600000));
        system.createAuction(auction);

        User bidder = new User("Adapa chaitanya", "password");
        system.registerUser(bidder);
        auction.placeBid(bidder, 550.0);

        system.viewAuctionDetails(auction);
    }
}
