<!-- Delete Item-->
function confirm_delete_item(id) {
    if (window.confirm('Are you sure you want to delete?')) {
        $.ajax({
            type : "DELETE",
            url : "/delete/"+ id,
            success : function() {
                window.location.reload();
            },
            error : function() {
                alert("Something didn't work well! (Error 405)");
            }
        });
    }
}

<!-- Delete Item from View page-->
function confirm_delete_item_from_view(id) {
    if (window.confirm('Are you sure you want to delete?')) {
        $.ajax({
            type : "DELETE",
            url : "/delete/"+ id,
            success : function() {
                window.location=document.referrer;
            },
            error : function() {
                alert("Something didn't work well! (Error 405)");
            }
        });
    }
}

<!-- Delete Category-->
function confirm_delete_category(id) {
    var actual = document.URL;
    if (window.confirm('Are you sure you want to delete?' +
        '\nWhen you delete this category, all the items who belong of this category, will be delete!')) {
        $.ajax({
            type : "DELETE",
            url : "/category/delete/"+ id,
            success : function() {
                window.location.replace(actual);
            },
            error : function() {
                alert("Something didn't work well! (Error 405)");
            }
        });
    }
}

<!-- Validate Save Category-->
$(document).ready(function() {
    $("#newCategory #categoryName").keyup(function() {
        document.getElementById("errorCategory").style.display = "none";
        document.getElementById("saveCategory").disabled = false;
        var name = $("#newCategory #categoryName").val();
        if (name == '') {
            errorSaveCategory("Required field! Please type something!");
            return;
        };
        if (name.length < 3 || name.length > 20) {
            errorSaveCategory("The category name must be 3 to 20 characters in length!");
            return;
        };
        axios.get('/categories/' + name)
            .then(function (response) {
                if (response.data){
                console.log(response.data);
                    errorSaveCategory("The name already exists! Please try something else!");
                };
            });
    });
});

function errorSaveCategory(message) {
    document.getElementById("saveCategory").disabled = true;
    document.getElementById("errorCategory").style.display = "block";
    $("#newCategory #errorCategory").text(message);
}