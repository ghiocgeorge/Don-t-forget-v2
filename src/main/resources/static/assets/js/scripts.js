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

<!-- Validate Add Name Category-->
$(document).ready(function() {
    $("#newCategory #categoryName").keyup(function() {
        document.getElementById("errorAddCategoryName").style.display = "none";
        document.getElementById("submitAddCategory").disabled = false;
        var inputName = $("#newCategory #categoryName").val();
        if (!validateCategoryName(inputName)) {
            return errorAddCategoryName("The name must be between 3 and 17 characters and contain only uppercase or " +
                "lowercase letters, numbers, or only the '_' and '.' special characters!");
        }
        axios.get('/categories/' + inputName)
            .then(function (response) {
                if (response.data){
                    errorAddCategoryName("The name already exists! Please try something else!");
                };
            });
    });
});

<!-- Validate Add Category Form-->
function validateAddCategory() {
    document.getElementById("errorAddCategoryDescription").style.display = "none";
    document.getElementById("errorAddCategoryName").style.display = "none";

    // Validate the name
    var inputName = $("#newCategory #categoryName").val();
    if (!validateCategoryName(inputName)) {
        errorAddCategoryName("The fields is required. Please type something!");
        return false;
    }

    // Validate the description
    var inputDescription = $("#newCategory #categoryDescription").val();
    if (!validateCategoryDescription(inputDescription)) {
        document.getElementById("errorAddCategoryDescription").style.display = "block";
        $("#newCategory #errorAddCategoryDescription").text("The description must be between 1 and 30 characters!");
        return false;
    }
    return true;
}

<!-- Show a category by id in a modal form-->
function update_category(id) {
    axios.get('/category/' + id)
        .then(async function (response) {
            $("#updateCategory #categoryId").val(response.data.id);
            $("#updateCategory #iconCategory").val(response.data.icon);
            $("#updateCategory #colorCategory").val(response.data.color);
            $("#updateCategory #categoryName").val(response.data.name);
            $("#updateCategory #categoryDescription").val(response.data.description);
            iconEdit();
            colorEdit();
        });
    document.getElementById('update_category').style.display='block';
}

<!-- Validate Edit Name Category-->
$(document).ready(function() {
    $("#updateCategory #categoryName").keyup(function() {
        document.getElementById("errorEditCategoryName").style.display = "none";
        document.getElementById("submitEditCategory").disabled = false;
        var inputName = $("#updateCategory #categoryName").val();
        if (!validateCategoryName(inputName)) {
            return errorEditCategoryName("The name must be between 3 and 17 characters and contain only uppercase or " +
                "lowercase letters, numbers, or only the '_' and '.' special characters!");
        }
        var id = $("#updateCategory #categoryId").val();
        axios.get('/category/' + id)
            .then(function (response) {
                var name = response.data.name;
                if (!(name.toUpperCase() === inputName.toUpperCase())) {
                    axios.get('/categories/' + inputName)
                        .then(function (response) {
                            if (response.data){
                                errorEditCategoryName("The name already exists! Please try something else!");
                            };
                        });
                };
            });
    });
});

<!-- Validate Edit Category Form-->
function validateUpdateCategory() {
    document.getElementById("errorEditCategoryDescription").style.display = "none";

    // Validate the description
    var inputDescription = $("#updateCategory #categoryDescription").val();
    if (!validateCategoryDescription(inputDescription)) {
        document.getElementById("errorEditCategoryDescription").style.display = "block";
        $("#updateCategory #errorEditCategoryDescription").text("The description must be between 1 and 30 characters!");
        return false;
    }
    return true;
}

function validateCategoryName(inputName) {
    var regex = /^[A-Za-z0-9_.]{3,17}$/g;
    return regex.test(inputName);
}

function validateCategoryDescription(inputDescription) {
    var regex = /^[A-Za-z0-9\s\W_]{1,30}$/g;
    return regex.test(inputDescription);
}

<!-- Show error message for Add Category Name -->
function errorAddCategoryName(message) {
    document.getElementById("submitAddCategory").disabled = true;
    document.getElementById("errorAddCategoryName").style.display = "block";
    $("#newCategory #errorAddCategoryName").text(message);
}

<!-- Show error message for Edit Category Name -->
function errorEditCategoryName(message) {
    document.getElementById("submitEditCategory").disabled = true;
    document.getElementById("errorEditCategoryName").style.display = "block";
    $("#updateCategory #errorEditCategoryName").text(message);
}

<!-- View the icon in add category form -->
$(document).ready(function() {
    $("#newCategory #iconCategory").change(async function() {
        var inputIcon = $("#newCategory #iconCategory").val();
        let result = await axios.get('/icons/' + inputIcon);
        $("#newCategory #colorAdd").html("<i id='iconAdd' class='" + result.data + "' style='font-size: 30px;'></i>");
    });
});

<!-- View the color in add category form -->
$(document).ready(function() {
    $("#newCategory #colorCategory").change(function() {
        var inputColor = $("#newCategory #colorCategory").val();
        $("#newCategory #colorAdd").css("color", inputColor);
    });
});

<!-- View the icon in edit category form -->
async function iconEdit() {
    var inputIcon = $("#updateCategory #iconCategory").val();
    let result = await axios.get('/icons/' + inputIcon);
    $("#updateCategory #colorAdd").html("<i class='" + result.data + "' style='font-size: 30px;'></i>");
}

$(document).ready(function() {
    $("#updateCategory #iconCategory").change(function() {
        iconEdit();
    });
});

<!-- View the color in edit category form -->
function colorEdit() {
    var inputColor = $("#updateCategory #colorCategory").val();
    $("#updateCategory #colorAdd").css("color", inputColor);
}

$(document).ready(function() {
    $("#updateCategory #colorCategory").change(function() {
        colorEdit();
    });
});

<!-- Switch the Add Item Modal to Add Category Modal -->
function switchAddItemToAddCategory() {
    document.getElementById('add_item').style.display='none';
    document.getElementById('add_category').style.display='block';
}

<!-- Show a item by id in view_item modal form-->
function view_item(id) {
    axios.get('/item/' + id)
        .then(function (response) {
            $("#viewItem #itemId").val(response.data.id);
            $("#viewItem #itemCategory").val(response.data.category.name);
            $("#viewItem #itemName").val(response.data.name);
            $("#viewItem #itemExpirationDate").val(response.data.expirationDate);

            if (response.data.description == '' || response.data.description == null) {
                $("#viewItem #itemDescription").val("No description");
            } else {
                $("#viewItem #itemDescription").val(response.data.description);
            }
        });
    document.getElementById('view_item').style.display='block';
}

<!-- Show a item by id in update_item modal form-->
function edit_item(id) {
    axios.get('/item/' + id)
        .then(function (response) {
            $("#updateItem #itemId").val(response.data.id);
            $("#updateItem #itemCategory").val(response.data.category.id);
            $("#updateItem #itemName").val(response.data.name);
            $("#updateItem #itemDescription").val(response.data.description);
            $("#updateItem #itemExpirationDate").val(response.data.expirationDate);
        });
    document.getElementById('update_item').style.display='block';
}

<!-- Switch the Edit Item Modal to Add Category Modal -->
function switchEditItemToAddCategory() {
    document.getElementById('update_item').style.display='none';
    document.getElementById('add_category').style.display='block';
}

<!-- Validate Add Item Form -->
function validateAddItem() {
    document.getElementById("errorAddSelectCategory").style.display = "none";
    document.getElementById("errorAddItemName").style.display = "none";
    document.getElementById("errorAddItemDate").style.display = "none";
    document.getElementById("errorAddItemDescription").style.display = "none";

    // Validate if is selected a category
    var itemCategory = $("#newItem #itemCategory").val();
    if (itemCategory == '' || itemCategory == null) {
        document.getElementById("errorAddSelectCategory").style.display = "block";
        $("#newItem #errorAddSelectCategory").text("Please select a category!");
        return false;
    }

    // Validate the name
    var inputName = $("#newItem #itemName").val();
    if (!validateItemName(inputName)) {
        document.getElementById("errorAddItemName").style.display = "block";
        $("#newItem #errorAddItemName").text("The name must be between 3 and 20 characters and contain only " +
            "uppercase or lowercase letters, numbers, spaces or only the '-' or '.' special characters!");
        return false;
    }

    // Validate the Description
    var inputDescription = $("#newItem #itemDescription").val();
    if (!validateItemDescription(inputDescription)) {
        document.getElementById("errorAddItemDescription").style.display = "block";
        $("#newItem #errorAddItemDescription").text("The description must be a maximum 255 characters!");
        return false;
    }

    // Validate if is selected an expiration date
    var itemDate = $("#newItem #itemExpirationDate").val();
    if (!validateDate(itemDate)) {
        document.getElementById("errorAddItemDate").style.display = "block";
        $("#newItem #errorAddItemDate").text("Please enter an expiration date that respects the YYYY-MM-DD pattern!");
        return false;
    }
    return true;
}

<!-- Validate Edit Item Form-->
function validateUpdateItem() {
    document.getElementById("errorEditItemName").style.display = "none";
    document.getElementById("errorEditItemDate").style.display = "none";
    document.getElementById("errorEditItemDescription").style.display = "none";

    // Validate the name
    var inputName = $("#updateItem #itemName").val();
    if (!validateItemName(inputName)) {
        document.getElementById("errorEditItemName").style.display = "block";
        $("#updateItem #errorEditItemName").text("The name must be between 3 and 20 characters and contain only " +
            "uppercase or lowercase letters, numbers, spaces or only the '-' or '.' special characters!");
        return false;
    }

    // Validate the Description
    var inputDescription = $("#updateItem #itemDescription").val();
    if (!validateItemDescription(inputDescription)) {
        document.getElementById("errorEditItemDescription").style.display = "block";
        $("#updateItem #errorEditItemDescription").text("The description must be a maximum 255 characters!");
        return false;
    }

    // Validate if is selected an expiration date
    var itemDate = $("#updateItem #itemExpirationDate").val();
    if (!validateDate(itemDate)) {
        document.getElementById("errorEditItemDate").style.display = "block";
        $("#updateItem #errorEditItemDate").text("Please enter an expiration date that respects the " +
            "YYYY-MM-DD pattern!");
        return false;
    }
    return true;
}

function validateItemName(inputName) {
    var regex = /^[A-Za-z0-9-\s.]{3,20}$/g;
    return regex.test(inputName);
}

function validateItemDescription(inputDescription) {
    var regex = /^[A-Za-z0-9\s\W_]{0,255}$/g;
    return regex.test(inputDescription);
}

function validateDate(itemDate) {
    var regex = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
    return regex.test(itemDate);
}

/* The category is automatically selected depending
   on wich html page a new item is added to */
function add_item(id) {
    $("#newItem #itemCategory").val(id);
    document.getElementById('add_item').style.display="block";
}