package orders;

import exceptions.OrderException;

public class OrderFactory {

    private static final int INDEX_ORDER_NAME = 0;
    private static final String REVERSE_ORDER = "REVERSE";

    /**
     * creates the appropriate object Order according to the proper string from the command file.
     * @param orderLine a line from the command file. The string representing the order in which to print out
     *                  the files from directory
     * @return the needed order
     * @throws OrderException throws an order exception in case the line is not a proper string we can read,
     */
    public static Order createOrder(String orderLine) throws OrderException {
        String[] splitOrderLine = orderLine.split("#");
        if (splitOrderLine.length == 0) {
            // line was empty
            throw new OrderException();
        }
        boolean isReverse = splitOrderLine[splitOrderLine.length - 1].equals(REVERSE_ORDER);
        String orderName = splitOrderLine[INDEX_ORDER_NAME];
        Order returnOrder;
        try {
            switch (orderName) {
                case "abs":
                    returnOrder = new AbsOrder();
                    break;
                case "type":
                    returnOrder = new TypeOrder();
                    break;
                case "size":
                    returnOrder = new SizeOrder();
                    break;
                default:
                    throw new OrderException();
            }

            if (isReverse) { returnOrder = new ReverseOrder(returnOrder); }

            return returnOrder;

            //todo any other exception????
        } catch (ArrayIndexOutOfBoundsException | UnsupportedOperationException exception) {
            throw new OrderException();
        }
    }

    /**
     * creates the default order (abs order)
     * @return the default order
     */
    public static Order createDefaultOrder(){
        return new AbsOrder();
    }
}
