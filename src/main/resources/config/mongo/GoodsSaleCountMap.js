function () {

    this.subOrderList.forEach(function(item){
        var key = item.type;
        var value = {
            "purchaseQuantity":0,
            "totalAmount":0
        };
        value.purchaseQuantity = item.purchaseQuantity;
        value.totalAmount = item.totalAmount;

        if(typeof(typeArg) != "undefined"  && typeArg !== null){
            if(item.type === typeArg){
                emit(key, value);
            }
        }else{
            emit(key, value);
        }

    });

}

