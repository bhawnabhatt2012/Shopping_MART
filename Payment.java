package shoppingMart;

public interface Payment {

    abstract void prepPayment(int total);        // Driver function for payment

    class Portal {

        // Card details
        private long cardNumber;
        private int expiryMonth;
        private int expiryYear;
        private String name;
        private int cvv;

        public long getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
        }

        public int getExpiryMonth() {
            return expiryMonth;
        }

        public void setExpiryMonth(int expiryMonth) {
            this.expiryMonth = expiryMonth;
        }

        public int getExpiryYear() {
            return expiryYear;
        }

        public void setExpiryYear(int expiryYear) {
            this.expiryYear = expiryYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCvv() {
            return cvv;
        }

        public void setCvv(int cvv) {
            this.cvv = cvv;
        }
    }

}