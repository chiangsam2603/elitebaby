let meals = [];
let userId = 1;
let total1 = null;
let total2 = null;

//依使用者會員編號取得訂單詳細資料
fetch("/elitebaby/MealOrder?name=getorder", {
    method: "POST",
    headers: {
        "Content-Type": "application/json;charset=UTF-8",
        "Access-Control-Allow-Origin": "*",
    },
    body: JSON.stringify(
        {
            userId: userId,
        }
    ),
})
    .then((resp) => {
        if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
        } else {
            return resp.json();
        }
    })
    .then((body) => {
        meals = body;
        try {
            if (body.length != null) {
                meals = body;
                let td_str = "";
                let img_str = "";
                for (let i = 0; i < body.length; i++) {
                    // let status = "";
                    console.log(body[i].strStatus);
                    if (body[i].strStatus == "取消") {
                        continue;
                    }
                    total1 += body[i].mealPrice * body[i].count;
                    // if (body[i].base64 == null || body[i] == "") {
                    //     img_str = `<td><img src="" id="mealPic"></td>`
                    // } else {
                    //     img_str = `<td><img src="data:image/png;base64,${body[i].base64}" id="mealPic"></td>`
                    // }
                    td_str += `
                        <tr data-id="${body[i].mealOrderId}">
                            <td>${body[i].orderDate}</td>
                            <td>${body[i].mealOrderId}</td>
                            <td>${body[i].total}</td>
                            <td>${body[i].strStatus}</td>
                            <td>${body[i].strPayment}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_detail" id="btn_detail" data-bs-toggle="modal" data-bs-target="#order_detail"
                                       data-bs-whatever="@fat">明細</button>
                                </div>
                            </td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_cancel" id="btn_cancel" data-bs-toggle="modal" data-bs-target="#cancel"
                                       data-bs-whatever="@fat">取消</button>
                                </div>
                            </td>
                        </tr>
                        `;
                    // console.log(body[i].base64);
                }
                $("tbody.getuserorder_tb").html(td_str);
                // $("div.total").html(total1 + " 元");
                // $("div.getall").html(body[0].mealId);
            }
        } catch (error) {
            console.log(error + "，回傳失敗");
        }
    });


let data_id = null;
let order_status = null;

//
$("tbody.getuserorder_tb").on("click", "button.btn_cancel", function () {
    // console.log("tttt");
    data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    for (let i = 0; i < meals.length; i++) {
        if (meals[i].mealOrderId == data_id) {
            console.log(meals[i].mealOrderId);
            console.log(meals[i].strStatus);
            order_status = meals[i].strStatus;
        }
    }
})

//
$("button.btn_tocheckout").on("click", function () {
    // console.log("yyyy");
    fetch("/elitebaby/MealOrder?name=update", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                mealOrderId: data_id,
                strStatus: order_status
            }
        ),
    })
        .then(resp => {
            if (resp.status === 204) {
                console.log("resp.status===" + resp.status);
            } else {
                return resp.json();
            }
        })
        .then((body) => {
            try {
                if (body != null) {
                    if (body.msg == "success") {
                        alert("取消成功!");
                        // $("span#cartCount").html(body.cartcount);
                        data_id = null;
                        location.reload();
                    } else {
                        console.log("取消失敗!");
                        alert("取消失敗!");
                    }
                }

            } catch (error) {
                console.log(error + "，資料庫沒照片故後端沒回傳");
            }
        });
})

//明細按鈕按下後發送請求訂單明細內容
$("tbody.getuserorder_tb").on("click", "button.btn_detail", function () {
    // console.log("rtyrty");
    data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    fetch("/elitebaby/MealOrder?name=getorderdetail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                userId: userId,
                mealOrderId: data_id
            }
        ),
    })
        .then((resp) => {
            if (resp.status === 204) {
                console.log("resp.status===" + resp.status);
            } else {
                return resp.json();
            }
        })
        .then((body) => {
            meals = body;
            try {
                if (body.length != null) {
                    // for (let i = 0; i < body.length; i++) {
                    //     console.log(body[i].mealOrderDetailId);
                    //     console.log(body[i].mealId);
                    //     console.log(body[i].mealName);
                    //     console.log(body[i].orderCount);
                    //     console.log(body[i].mealPrice);
                    // }
                    // alert("查詢成功");
                    let td_str = "";
                    let img_str = "";
                    for (let i = 0; i < body.length; i++) {
                        // let status = "";
                        // console.log(body[i].strStatus);
                        if (body[i].strStatus == "取消") {
                            continue;
                        }
                        console.log(typeof (body[i].mealPrice));
                        console.log(typeof (body[i].orderCount));
                        total2 += body[i].mealPrice * body[i].orderCount;
                        console.log(total2);
                        if (body[i].base64 == null || body[i] == "") {
                            img_str = `<td><img src="" id="mealPic"></td>`
                        } else {
                            img_str = `<td><img src="data:image/png;base64,${body[i].base64}" id="mealPic"></td>`
                        }
                        td_str += `
                                <tr data-id="${body[i].mealId}">
                                    <td>${body[i].mealId}</td>
                                    <td>${body[i].mealName}</td>
                                    ${img_str}
                                    <td>${body[i].mealPrice}</td>
                                    <td>${body[i].orderCount}</td>
                                    <td>${body[i].mealPrice * body[i].orderCount}</td>
                                </tr>
                                `;
                        // console.log(body[i].base64);
                    }

                    $("tbody.getuserorderdetail_tb").html(td_str);
                    $("span.detail_total").html("$" + total2);
                    // console.log("123");
                    // $("div.total").html(total + " 元");
                    // $("div.getall").html(body[0].mealId);
                }
            } catch (error) {
                console.log(error + "，回傳失敗");
            }
        });
})


