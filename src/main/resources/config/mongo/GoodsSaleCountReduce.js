function (key, values) {
    var value = {
        "purchaseQuantity":0,
        "totalAmount":0
    };
    values.forEach(function(item){
        value.purchaseQuantity += item.purchaseQuantity;
        value.totalAmount +=NumberInt(item.totalAmount);
    });
    return value;
}