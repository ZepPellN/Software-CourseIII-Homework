
//span:days grade:points BRH:broswer repositories
function loadEvaluation(span, grade, BRH){
	var description;
	var ratio1 = parseFloat(BRH) / span;
	var ratio2 = parseFloat(grade) / span;

	if(span < 30){
		description = span1(ratio1, ratio2);
	}else if(span < 90){
		description = span2(ratio1,ratio2);
	}else if(span < 365){
		description = span3(span,grade,BRH);
	}else{
		description = span4(span,grade,BRH);
	}

	return description;
}

function span1(ratio1, ratio2){
	var description;
	description = "You have just caught a glimpse of a tip of iceberg.";
	if(ratio1 < 1){
		description += "However, you could struggle harder and browse more repositories."
	}else if(ratio1 < 5){
		description += "You make an effort.Keep going and explore more repository.";
	}else if(ratio1 < 9){
		description += "It's exhilarating to see how proactive you are.Hold on!";
	}else{
		description += "Your enthusiasm is admirable.But you'd better browse fewer and choose one to dive in.";
	}
	if(ratio2 < 10){
		description += "And you have obtained some achievements.Learn more and you will be more interested."
	}else if(ratio2 < 30){
		description += "And it's rough to insist on learning.Your perservance can also be your weapon.";
	}else if(ratio2 < 50){
		description += "You must be a genius and your future is boundless.";
	}else{
		description += "Your ability is incredible but cramming is not appropriate.";
	}	
	description += "The beauty of programming waits to be excavated."
	return description;
}

//dong yibo
function span2(ratio1,ratio2){
	var str;
	var ratio=Math.sqrt(ratio1*ratio2);
	if(ratio<1){
		str="You can try more!";
	}
	else if(ratio<5){
		str="You have grown up a little";
	}
	else if(ratio<10){
		str="Come on, you can get more";
	}
	else if(ratio<20){
		str="Experience the fun of the Gitmining?";
	}
	else if(ratio<50){
		str="Your ability has risen a lot!";
	}
	else{
		str="You can dig out more interesting things"
	}
	return str;
}
//guhan
function span3(span, grade, BRH){
	var ratio1 = grade/BRH;					//average complexity of repositories studied
	var ratio2 = BRH/span;					//average browse records per day
	if(ratio1 >= 20 && ratio1<50)
		return "Catch up! Chanllenge yourself for more complex repositories";
	if(ratio1 >=50 && ratio1<80){
		if(span==100)
			return "You have made a 100-day streak! Keep going!";
		if(ratio2 > 30)
			return "Quick of eye and deft of hand";
		if(ratio2 < 3)
			return "Wise men always view a lot";
	}
	if(ratio1 >=80){
		return "You are approaching an Einstein's brain, solving high-level complexity";
	}
	return "Day\ "+span+"\ since you joined us. How about your learning?";
	
}

//zhang shiqi
function span4(span,grade,BRH){
	var description = "Senior Gamer,";
	var ratio1 = parseFloat(BRH) / span;	//repositories read per day
	var ratio2 = parseFloat(grade) / span;	//grades  earned per day
	var ratio3 = grade / parseFloat(BRH);	//grades earned per repository
	var ratio =  ratio1 * ratio2;
	
	if(ratio < 1){
		description += "Studying slowly and deliberately,";
	}else if(ratio < 10){
		description += "Studying smoothly forward,";
	}else if(ratio < 80){
		description += "Studying wingfooted,";
	}else if(ratio < 200){
		description += "Studying quickly as a flash of lighting,";
	}else {
		description += "Studying die hard,"
	}
	
	if(ratio < 50){
		description += "Mostly learning level 1 and level 2 repositories,Be brave to learn the top level.";
	}else if(ratio < 100){
		description += "Code master.";
	}
		
	return description;
}