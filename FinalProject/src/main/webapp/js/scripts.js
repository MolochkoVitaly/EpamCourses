/*language dropdown*/
function DropDown(el) {
    this.dd = el;
    this.initEvents();
}

DropDown.prototype = {
    initEvents : function() {
        var obj = this;

        obj.dd.on('click', function(event){
            $(this).toggleClass('active');
            //return false;
        });
    }
};

$(document).ready(function() {
    var dd = new DropDown($('#dd'));

    $("[data-tooltip]").mousemove(function (eventObject) {

        $data_tooltip = $(this).attr("data-tooltip");

        $("#tooltip").text($data_tooltip)
            .css({
                "top" : eventObject.pageY + 5,
                "left" : eventObject.pageX + 5
            })
            .show();

    }).mouseout(function () {
        $("#tooltip").hide()
            .text("")
            .css({
                "top" : 0,
                "left" : 0
            });
    });

    var alertWarning = function (message) {
        return '<div class="alert alert-warning alert-dismissible fade in result" role="alert">' +
            '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
            '  <span class="result-text">' + message + '</span></div>';
    };

    var alertError = function (message) {
        return '<div class="alert alert-error alert-dismissible fade in result" role="alert">' +
            '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
            '  <span class="result-text">' + message + '</span></div>';
    };

    var alertSuccess = function (message) {
        return '<div class="alert alert-success alert-dismissible fade in result" role="alert">' +
            '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
            '  <span class="result-text">' + message + '</span></div>';
    };

    $('.nav-tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');

    });

    $(".container div.result").on("click", ".close", function () {
        $(".container").find(".result").css("display", "none").hide();
    });

    $("div .subscribe").on("click", ".btn", function () {
        var data = function (id) {
            return {
                command: "subscribe",
                id: id
            };
        };
        var id = $(this).parent().parent().parent().attr("id");

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data(id)),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                debugger;
                $(".container table").before(alertSuccess(responseText.text)).show();
                $("#subscribeModal").find("input[name=id]").val("");
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            },
            error: function (responseText) {
                $(".container table").before(alertWarning(JSON.parse(responseText.responseText).text)).show();
                $("#subscribeModal").find("input[name=id]").val("");
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });
    });

    $("#editData").click(function () {
        var data = $(".row .label_box");
        var modalData = $("#editDataModal").find("input");
        for (var i = 0; i < data.length; i++) {
            modalData[i + 1].value = data[i].textContent;
        }
    });

    $('#send-review').click(function () {
        var textArea = $('#textReview');
        var theReview = function (text) {
            return {
                command: "review",
                text: text
            };
        };
        var textReview = textArea.val();
        if (textReview != "") {
            var task = theReview(textReview);
            $.ajax({
                type: "POST",
                url: "ajaxController",
                data: JSON.stringify(task),
                dataType: "json",
                async: false,
                headers: {"Access-Control-Allow-Origin": "*"},
                contentType: "application/json; charset=utf-8",
                success: function (responseText) {
                    updateReviews();
                },
                error: function (responseText) {
                    $(".container .reviewsScroll").before(alertError(JSON.parse(responseText.responseText).text)).show();
                    setTimeout(function () {
                        $("div.alert button[type=button]").trigger('click');
                    }, 5000);
                }
            });
            textArea.val('');
        }
        updateReviews();
        //document.getElementById("showMessage").scrollTop = document.getElementById("showMessage").scrollHeight;
    });


    $("#addTariff").click(function () {
        var theTariff = function (name, description, upload, download, volume, price) {
            return {
                command: "addTariff",
                name: name,
                description: description,
                upload: upload,
                download: download,
                volume: volume,
                price: price
            };
        };
        var name = $("#name").val();
        var description = $("#description").val();
        var upload = $("#upload").val();
        var download = $("#download").val();
        var volume = $("#volume").val();
        var price = $("#price").val();
        var newTariff = theTariff(name, description, upload, download, volume, price);
        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(newTariff),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $("#tariff-tools").find(".row").before(alertSuccess(responseText.text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            },
            error: function (responseText) {
                $("#tariff-tools").find(".row").before(alertWarning(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });

    });

    $(".find-tariff button").click(function () {
        var data = function () {
            return {
                command: "getTariffs"
            };
        };

        var liType = function (id, tariffName) {
            return '<li tariff-id=' + id + '><a>' + tariffName + '</a></li>';
        };

        function create(allTariffs) {
            for (var i = 0; i < allTariffs.length; i++) {
                var tariff = JSON.parse(allTariffs[i]);
                addTariff(tariff);
            }

            $(".find-tariff .dropdown-menu a").click(function () {
                var id = $(this).parent().attr("tariff-id");
                loadSelectedTariff(id);
            });
        }

        function addTariff(tariff) {
            $(".find-tariff .dropdown-menu").append(liType(tariff.id, tariff.name));
        }

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data()),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $(".find-tariff .dropdown-menu").children().remove();
                create(responseText.tariffs);
                $("#tariff-tools").find(".row")
            },
            error: function (responseText) {
                $("#tariff-tools").find(".row").before(alertError(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });
    });

    var loadSelectedTariff = function (id) {
        var data = function () {
            return {
                command: "findSelectedTariff",
                id: id
            };
        };

        function addInForm(tariff) {
            $("#id").val(tariff.id);
            $("#name").val(tariff.tariffName);
            $("#description").val(tariff.description);
            $("#upload").val(tariff.uploadSpeed);
            $("#download").val(tariff.downloadSpeed);
            $("#volume").val(tariff.trafficVolume);
            $("#price").val(tariff.monthPayment);
        }

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data()),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                addInForm(responseText.tariff);
                $("#addTariff").attr("type", "hidden");
                $("#editTariff").attr("type", "submit");
                $("#archive").attr("type", "submit");
                $("#cancel").attr("type", "submit");
            },
            error: function (responseText) {
                $("#tariff-tools").find(".row").before(alertError(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);

            }
        });
    };

    $("#cancel").click(function () {
        $("#addTariff").attr("type", "submit");
        $("#editTariff").attr("type", "hidden");
        $("#archive").attr("type", "hidden");
        $("#cancel").attr("type", "hidden");
        $("#id").val("");
        $("#name").val("");
        $("#description").val("");
        $("#upload").val("");
        $("#download").val("");
        $("#volume").val("");
        $("#price").val("");
    });

    $("#editTariff").click(function () {
        var theTariff = function (id, name, description, upload, download, volume, price) {
            return {
                command: "editTariff",
                id: id,
                name: name,
                description: description,
                upload: upload,
                download: download,
                volume: volume,
                price: price
            };
        };

        var id = $("#id").val();
        var name = $("#name").val();
        var description = $("#description").val();
        var upload = $("#upload").val();
        var download = $("#download").val();
        var volume = $("#volume").val();
        var price = $("#price").val();
        var newTariff = theTariff(id, name, description, upload, download, volume, price);
        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(newTariff),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $("#tariff-tools").find(".row").before(alertSuccess(responseText.text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            },
            error: function (responseText) {
                $("#tariff-tools").find(".row").before(alertWarning(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);

            }
        });

    });

    $("#archive").click(function () {
        var idData = function (id) {
            return {
                command: "transferToArchive",
                id: id
            };
        };

        var id = $("#id").val();
        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(idData(id)),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $("#tariff-tools").find(".row").before(alertSuccess(responseText.text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            },
            error: function (responseText) {
                $("#tariff-tools").find(".row").before(alertError(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });
    });

    $("#upBalance").click(function () {
        var data = function (sum) {
            return {
                command: "replenishBalance",
                sum: sum
            };
        };
        var inputBalance = $("#money").val();
        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data(inputBalance)),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $("#money").val("");
                $(".balance").before(alertSuccess(responseText.text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            },
            error: function (responseText) {
                $("#money").val("");
                $(".balance").before(alertWarning(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });

        data = function () {
            return {
                command: "showBalance"
            };
        };

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data()),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                $(".current-balance h2 span#count").text(responseText.balance);
            },
            error: function (responseText) {
                $("#payment").find(".balance").before(alertError(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });
    });

    function updateReviews() {
        var data = function () {
            return {
                command: "updateReviews"
            };
        };

        var blockquoteType = function (id, text, userName, userSurname, date) {
            return '<blockquote class="blockquote-reverse" review-id="' + id + '">' +
                 '<span class="delete-review" style="float: left"><i class="fa fa-times" aria-hidden="true"></i></span>' +
                 '<p>' + text + '</p>'+
                 '<footer>' + userName + ' ' + userSurname + '</footer>' +
                 '<footer>' + date + '</footer>' +
                 '</blockquote>';
        };

        function create(allReviews) {
            for (var i = 0; i < allReviews.length; i++) {
                var review = allReviews[i];
                addReview(review);
            }
        }

        function addReview(review) {
            $("#reviews-area").append(blockquoteType(review.id, review.text, review.userName, review.userSurname, review.date));
        }

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data()),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                debugger;
                $("#reviews-area").children().remove();
                create(responseText.reviews);
            },
            error: function (responseText) {
                $(".container .reviewsScroll").before(alertError(JSON.parse(responseText.responseText).text)).show();
                setTimeout(function () {
                    $("div.alert button[type=button]").trigger('click');
                }, 5000);
            }
        });
    }

    $("#reviews-area").find(".delete-review").click(function () {
        var data = function (id) {
            return {
                command: "deleteReview",
                id:id
            };
        };

        var id = $(this).parent().attr("review-id");

        $.ajax({
            type: "POST",
            url: "ajaxController",
            data: JSON.stringify(data(id)),
            dataType: "json",
            async: false,
            headers: {"Access-Control-Allow-Origin": "*"},
            contentType: "application/json; charset=utf-8",
            success: function (responseText) {
                updateReviews();
            },
            error: function (responseText) {
                alert("was not deleted");
            }
        });
    });

});