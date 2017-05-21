package ii_collections

fun example8() {
    val numbers = listOf(1, 3, -4, 2, -11)

    // The details (how multi-assignment works) will be explained later in the 'Conventions' task
    val (positive, negative) = numbers.partition { it > 0 }

    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

fun Customer.getDeliveredAndUndeliveredOrders(): Pair<List<Order>, List<Order>> {
    return this.orders.partition { it.isDelivered }
}

fun Customer.hasMoreDeliveredOrders(): Boolean {
    val (delivered, undelivered) = this.getDeliveredAndUndeliveredOrders()
    return delivered.size >= undelivered.size
}

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
    // Return customers who have more undelivered orders than delivered
    val (_, undelivered) = this.customers.partition { it.hasMoreDeliveredOrders() }
    return undelivered.toSet()
}
