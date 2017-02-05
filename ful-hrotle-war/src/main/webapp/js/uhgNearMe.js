    function doModal() { //handles popout event
        $("#menu-close").click();
        $('#loginDiv').hide();
        $('#registerDiv').hide();
        $('#modalContent').show();
        var modal = document.getElementById('modal');
        modal.style.display = "block";
    }
    window.onclick = function(event) { //hides modal if click outside it
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    function closeModal() { //manually close modal by calling this
        var modal = document.getElementById('modal');
        modal.style.display = "none";
    }

    function doLogin() {
        $('#loginDiv').show();
        $('#modalContent').hide();
    }

    function doRegister() {
        $('#registerDiv').show();
        $('#modalContent').hide();
    }

    function makePlusIcons() {
        $('#joggingButton').attr('src', 'img/plusIcon.png');
        $('#cyclingButton').attr('src', 'img/plusIcon.png');
        $('#dancingButton').attr('src', 'img/plusIcon.png');
        $('#yogaButton').attr('src', 'img/plusIcon.png');
        $('#swimmingButton').attr('src', 'img/plusIcon.png');
        $('#rowingButton').attr('src', 'img/plusIcon.png');
        $('#taichiButton').attr('src', 'img/plusIcon.png');
        $('#gymButton').attr('src', 'img/plusIcon.png');
        $('#pilatesButton').attr('src', 'img/plusIcon.png');
    }
    function hideAllDivs() {
        $('#joggingDiv').hide();
        $('#cyclingDiv').hide();
        $('#dancingDiv').hide();
        $('#yogaDiv').hide();
        $('#swimmingDiv').hide();
        $('#rowingDiv').hide();
        $('#taichiDiv').hide();
        $('#gymDiv').hide();
        $('#pilatesDiv').hide();
        $('#joggingDivName').hide();
        $('#cyclingDivName').hide();
        $('#dancingDivName').hide();
        $('#yogaDivName').hide();
        $('#swimmingDivName').hide();
        $('#rowingDivName').hide();
        $('#taichiDivName').hide();
        $('#gymDivName').hide();
        $('#pilatesDivName').hide();
    }
    function submit() {
    //Creates file object that fetches data
    var file = new Object();
    file.condition = document.getElementById('conditionSelect');
    file.age = document.getElementById('ageSelect');
    file.weight = document.getElementById('weightSelect');
    file.zip = document.getElementById('zipSelect');

    //Sanitize data
    $.trim(file.age.value);
    $.trim(file.weight.value);
    $.trim(file.zip.value);
    if (isNaN(file.age.value)) {
        alert("Age must be a number");
        return;
    }
    if (isNaN(file.weight.value)) {
        alert("Weight must be a number");
        return;
    }
    if (isNaN(file.zip.value)) {
        alert("Zip code must be a number");
        return;
    }
    if (!file.age.value) {
        alert("Age cannot be empty");
        return;
    }
    if (!file.weight.value) {
        alert("Weight cannot be empty");
        return;
    }
    if (!file.zip.value) {
        alert("Zip code cannot be empty");
        return;
    }

    //process sends ajax request here
    var startDest = "http://uhgnearme-uhgnearme.ose-ctc-core.optum.com/api/ProviderTemplate/resource/interpath/";
    var slash = "/";
    var condString = String(file.condition.value);
    var ageString = String(file.age.value);
    var weightString = String(file.weight.value);
    var zipString = String(file.zip.value);
    var dest = startDest + condString + slash + ageString + slash + weightString + slash + zipString;
    $.ajax({
        url: dest,
        type: "GET",
        dataType: "json",
        beforeSend: function() {
            //hide search and create loader
            $('#searchBttn').remove();
            var loader = $('<img src="loading.gif" id="loaderGif" />');
            $('#subrightside').append(loader);
        },
        complete: function() {
            //hide loader and bring back search
            $('#loaderGif').remove();
            var search = $('<a onClick="submit();return false;" href="#" style="font-family:sans-serif;font-size:15px" class="btn btn-dark btn-lg" id="searchBttn" />');
            search.text("Search");
            $('#subrightside').append(search);
        },
        success: function(response) { //remove old data to make room for new
            //empty old data
            $('#subleftside').empty();
            $('#joggingDiv').empty();
            $('#cyclingDiv').empty();
            $('#dancingDiv').empty();
            $('#yogaDiv').empty();
            $('#swimmingDiv').empty();
            $('#rowingDiv').empty();
            $('#taichiDiv').empty();
            $('#gymDiv').empty();
            $('#pilatesDiv').empty();

            //hide divs to prep for new data
            hideAllDivs();

            $('#leftside').css('background', '').css('background', 'rgba(25,25,25,.6)').css('margin-top', '100px');
            $('#leftside').css('padding', '15px 50px 15px 50px').css('border-radius', '25px');
            $('#leftside').css('color', '#FFFFFF');

            //process data
            for (i = 0; i < 3; i++) {
                var newDiv;
                var activityNameText = JSON.stringify(response.user[i].activityLabel[0].activity).replace(/\"/g, '');
                switch(activityNameText) {
                    case "jogging":
                        newDiv = $('#joggingDiv');
                        $('#joggingDivName').show();
                        break;
                    case "cycling":
                        newDiv = $('#cyclingDiv');
                        $('#cyclingDivName').show();
                        break;
                    case "dancing":
                        newDiv = $('#dancingDiv');
                        $('#dancingDivName').show();
                        break;
                    case "yoga":
                        newDiv = $('#yogaDiv');
                        $('#yogaDivName').show();
                        break;
                    case "swimming":
                        newDiv = $('#swimmingDiv');
                        $('#swimmingDivName').show();
                        break;
                    case "rowing":
                        newDiv = $('#rowingDiv');
                        $('#rowingDivName').show();
                        break;
                    case "tai chi":
                        newDiv = $('#taichiDiv');
                        $('#taichiDivName').show();
                        break;
                    case "gym":
                        newDiv = $('#gymDiv');
                        $('#gymDivName').show();
                        break;
                    case "pilates":
                        newDiv = $('#pilatesDiv');
                        $('#pilatesDivName').show();
                        break;
                    default:
                        break;
                }

                var name = $("<p></p>").text(JSON.stringify(response.user[i].activityLabel[1].locations[0].business[0].name).replace(/\"/g, ''));
                name = name.addClass('businessName');
                $(newDiv).append(name);
                var address1 = $("<p></p>").text(JSON.stringify(response.user[i].activityLabel[1].locations[0].business[1].address[0]).replace(/\"/g, ''));
                var address2 = $("<p></p>").text(JSON.stringify(response.user[i].activityLabel[1].locations[0].business[1].address[1]).replace(/\"/g, ''));
                address1 = address1.addClass('address');
               	address2 = address2.addClass('address');
                $(newDiv).append(address1);
                $(newDiv).append(address2);
                var phone = $("<p></p>");
                phone = phone.addClass('phone');
                if (response.user[i].activityLabel[1].locations[0].business[2].phone !== null) {
                    phone.text(JSON.stringify(response.user[i].activityLabel[1].locations[0].business[2].phone).replace(/\"/g, ''));
                    var phone1 = "(" + JSON.stringify(response.user[i].activityLabel[1].locations[0].business[2].phone).replace(/\"/g, '').substring(0,3) + ")-";
                    var phone2 = JSON.stringify(response.user[i].activityLabel[1].locations[0].business[2].phone).replace(/\"/g, '').substring(3,6) + "-";
                    var phone3 = JSON.stringify(response.user[i].activityLabel[1].locations[0].business[2].phone).replace(/\"/g, '').substring(6);
                    phone.text("Phone: " + phone1 + phone2 + phone3);
                }
                else {
                    phone.text("No phone available.");
                }
                $(newDiv).append(phone);
                var rating = $("<p></p><br />").text("Rating: " + JSON.stringify(response.user[i].activityLabel[1].locations[0].business[4].rating).replace(/\"/g, '') + "/5");
                rating = rating.addClass('rating');
                $(newDiv).append(rating);
            }
        }
    });
			}
				
			String.prototype.capitalizeFirstLetter = function() {
    			return this.charAt(0).toUpperCase() + this.slice(1);
			}
    function doJogging() {
        if ($('#joggingDiv').is(':visible')) {
            $('#joggingButton').attr('src', 'img/plusIcon.png');
            $('#joggingDiv').hide();               
        }
        else {
            $('#joggingButton').attr('src', 'img/minusIcon.png');
            $('#joggingDiv').show();
        }
    }
    function doCycling() {
        if ($('#cyclingDiv').is(':visible')) {
            $('#cyclingButton').attr('src', 'img/plusIcon.png');
            $('#cyclingDiv').hide();               
        }
        else {
            $('#cyclingButton').attr('src', 'img/minusIcon.png');
            $('#cyclingDiv').show();
        }
    }
    function doDancing() {
        if ($('#dancingDiv').is(':visible')) {
            $('#dancingButton').attr('src', 'img/plusIcon.png');
            $('#dancingDiv').hide();               
        }
        else {
            $('#dancingButton').attr('src', 'img/minusIcon.png');
            $('#dancingDiv').show();
        }
    }
    function doYoga() {
        if ($('#yogaDiv').is(':visible')) {
            $('#yogaButton').attr('src', 'img/plusIcon.png');
            $('#yogaDiv').hide();               
        }
        else {
            $('#yogaButton').attr('src', 'img/minusIcon.png');
            $('#yogaDiv').show();
        }
    }
    function doSwimming() {
        if ($('#swimmingDiv').is(':visible')) {
            $('#swimmingButton').attr('src', 'img/plusIcon.png');
            $('#swimmingDiv').hide();               
        }
        else {
            $('#swimmingButton').attr('src', 'img/minusIcon.png');
            $('#swimmingDiv').show();
        }
    }
    function doRowing() {
        if ($('#rowingDiv').is(':visible')) {
            $('#rowingButton').attr('src', 'img/plusIcon.png');
            $('#rowingDiv').hide();               
        }
        else {
            $('#rowingButton').attr('src', 'img/minusIcon.png');
            $('#rowingDiv').show();
        }
    }
    function doTaiChi() {
        if ($('#taichiDiv').is(':visible')) {
            $('#taichiButton').attr('src', 'img/plusIcon.png');
            $('#taichiDiv').hide();               
        }
        else {
            $('#taichiButton').attr('src', 'img/minusIcon.png');
            $('#taichiDiv').show();
        }
    }
    function doGym() {
        if ($('#gymDiv').is(':visible')) {
            $('#gymButton').attr('src', 'img/plusIcon.png');
            $('#gymDiv').hide();               
        }
        else {
            $('#gymButton').attr('src', 'img/minusIcon.png');
            $('#gymDiv').show();
        }
    }
    function doPilates() {
        if ($('#pilatesDiv').is(':visible')) {
            $('#pilatesButton').attr('src', 'img/plusIcon.png');
            $('#pilatesDiv').hide();               
        }
        else {
            $('#pilatesButton').attr('src', 'img/minusIcon.png');
            $('#pilatesDiv').show();
        }
    }