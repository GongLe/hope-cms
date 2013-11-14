/**
 * 表单post提交到iframe,
 */
$(function () {
    var defaults = {
        iframeID: 'hidden_submit_iframe',       // Iframe ID.
        iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank',
        formID: 'hidden_submit_form',
        formAction: null,
        formMethod: 'POST',
        data: [],  //[{name:'parameter name',value:'parameter value' }]
        complete: null  // function (response) {  } // after response from the server has been received.

    };
    $.hiddenSubmit = function (options) {
        var iframe, form, $form  , extraInputs = [];
        options = $.extend({}, defaults, options);

        if (!$('#' + options.formID).length) {
            $form = $('<form/>');
            //setting form attr
        } else {
            //clear form
            $form = $('#' + options.formID);
            $form.html('');
        }
        //setting form
        $form.attr({
            id: options.formID,
            name: options.formID,
            method: options.formMethod,
            action: options.formAction,
            target: options.iframeID
        }).css('display', 'none')
            .appendTo('body');
        //setting form input
        //hide sitemesh parameter !!!
        options.data.push({name: '$SiteMesh', value: 'false'});

        for (var i = 0; i < options.data.length; i++) {
            $('<input type="hidden" name="' + options.data[i].name + '">').val(options.data[i].value)
                .appendTo($form);
        }

        // Add   iframe.
        if (!$('#' + options.iframeID).length) {
            $('body').append('<iframe id="' + options.iframeID + '" name="' + options.iframeID + '" src="' + options.iframeSrc + '" style="display:none" ></iframe>');
        }
        //on load
        iframe = $('#' + options.iframeID).load(function () {
            var response = iframe.contents()
            if ($.isFunction(options.complete))
                options.complete.apply($form[0], [response]);

            iframe.unbind('load');

        });
        //submit
        form = $form[0]
        try {
            form.submit();
        } catch (err) {
            // just in case form has element with name/id of 'submit'
            var submitFn = document.createElement('form').submit;
            submitFn.apply(form);
        }
    }


})