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

<!-- Validate Add Category-->
$(document).ready(function() {
    $("#newCategory #categoryName").keyup(function() {
        document.getElementById("errorAddCategory").style.display = "none";
        document.getElementById("submitAddCategory").disabled = false;
        var inputName = $("#newCategory #categoryName").val();
        if (!validateName(inputName))
            return errorSaveCategory("The name must be between 3 and 20 characters and contain only uppercase or " +
                "lowercase letters, numbers, or only the '_' and '.' special characters!");
        axios.get('/categories/' + inputName)
            .then(function (response) {
                if (response.data){
                    errorSaveCategory("The name already exists! Please try something else!");
                };
            });
    });
});

<!-- Show a category by id in a modal form-->
function update_category(id) {
    axios.get('/category/' + id)
        .then(function (response) {
            $("#updateCategory #categoryId").val(response.data.id);
            $("#updateCategory #categoryName").val(response.data.name);
            $("#updateCategory #categoryDescription").val(response.data.description);
        });
    document.getElementById('update_category').style.display='block';
}

<!-- Validate Edit Category-->
$(document).ready(function() {
    $("#updateCategory #categoryName").keyup(function() {
        document.getElementById("errorEditCategory").style.display = "none";
        document.getElementById("submitEditCategory").disabled = false;
        var inputName = $("#updateCategory #categoryName").val();
        if (validateName(inputName))
            return errorEditCategory("The name must be between 3 and 20 characters and contain only uppercase or " +
                "lowercase letters, numbers, or only the '_' and '.' special characters!");
        var id = $("#updateCategory #categoryId").val();
        axios.get('/category/' + id)
            .then(function (response) {
                var name = response.data.name;
                if (!(name.toUpperCase() === inputName.toUpperCase())) {
                    axios.get('/categories/' + inputName)
                        .then(function (response) {
                            if (response.data){
                                errorEditCategory("The name already exists! Please try something else!");
                            };
                        });
                };
            });
    });
});

function errorSaveCategory(message) {
    document.getElementById("submitAddCategory").disabled = true;
    document.getElementById("errorAddCategory").style.display = "block";
    $("#newCategory #errorAddCategory").text(message);
}

function errorEditCategory(message) {
    document.getElementById("submitEditCategory").disabled = true;
    document.getElementById("errorEditCategory").style.display = "block";
    $("#updateCategory #errorEditCategory").text(message);
}

function validateName(inputName) {
    var regex = /^[A-Za-z0-9_.]{3,20}$/g;
    return regex.test(inputName);
}