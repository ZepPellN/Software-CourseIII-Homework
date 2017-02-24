var thead = $("<thead style='font-size: 18px; font-weight: bold;'><tr><th>Language</th><th>Name</th><th></th><th><a style='color: #1C1B1A'>Stargazers</a></th><th><a style='color: #1C1B1A'>Forks</a></th><th><a style='color: #1C1B1A'>Last Update</a></th></tr></thead>");
var tbody = $('<tbody id="repo-list" style="font-size: 18px;"></tbody>');
var div1 = $("<div class='col-md-12'></div>")
var div2 = $("<div class='card'></div>");
var div3 = $("<div class='content table-responsive table-full-width'></div>");
var div4 = $("<table class='table table-hover'></table>");

function submitForm() {
	var lev = form.level.value;
	var lan = form.languages.value;
	$.ajax({
		url : form.action,
		type : form.method,
		data : {
			level : lev,
			language : lan
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var temp, name, stars, forks, last_update, description, homepage, language;

			for (var i = 0; i < data.length; i++) {
				temp = data[i];
				homepage = temp.homepage;
				description = temp.description;
				language = temp.language;
				name = temp.name;
				stars = temp.stars;
				forks = temp.forks;
				last_update = temp.last_update;

				var content = $("<tr></tr>");
				content.attr("height", "60px");
				content.attr("font-size", "18px");

				var languagebar = $("<td></td>");
				languagebar.text(language);

				var namebar = $("<td></td");
				var link = $("<a></a>");
				link.attr("onmousedown", "if(event.button == 2){openGithub('" + name + "');}else if(event.button == 0){refreshHistory('" + name + "');}return false;");
				link.text(name);
				namebar.append(link);

				var icontd = $("<td></td>");
				if (homepage != "") {
					var iconlink = $("<a target='_blank'></a>");
					iconlink.attr("style", "color:#1C1B1A");
					iconlink.attr("href", homepage);
					var icon = $("<i></i>");
					icon.attr("class", "ti-more-alt");
					iconlink.append(icon);
					icontd.append(iconlink);
				}

				var stargazersbar = $("<td></td>");
				stargazersbar.text(stars);

				var forksbar = $("<td></td");
				forksbar.text(forks);

				var last_updatebar = $("<td></td>");
				last_updatebar.text(last_update);

				content.append(languagebar, namebar, icontd,
						stargazersbar, forksbar, last_updatebar);

				tbody.append(content);
			}
			div4.append(thead, tbody);
			var table_title=$("<h3 style='text-align: center;'>Maybe You Will Be Interested</h3>");
			div3.append(div4);
			div2.append(div3);
			div1.append(table_title, div2);
			$("#initial_row").empty();
			$("#initial_row").append(div1);
		}
	});
}