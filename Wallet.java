public class Wallet implements ViewWallet
{
    private double balance;
    private boolean successDec;

    Wallet(double balance)
    {
        this.balance = balance;
    }

    public double get_balance()
    {
        return balance;
    }

    public void dec_balance(double amount)
    {
        try
        {
            if (amount <= 0)
            {
                throw new IllegalArgumentException("Amount can't be neagtive");
            }
            if (!isEnoughBalance(amount))
            {
                throw new IllegalArgumentException("Not enough balance to decrease " + amount);
            }
            balance -= amount;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error decreasing balance: " + e.getMessage());
        }
    }

    public void inc_balance(double amount)
    {
        try
        {
            if (amount <= 0)
            {
                throw new IllegalArgumentException("Amount can't be negative.");
            }
            balance += amount;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error increasing balance: " + e.getMessage());
        }
    }

    boolean isEnoughBalance(double amount)
    {
        if (amount >= balance)
            return true;
        else
            return false;
    }
}
