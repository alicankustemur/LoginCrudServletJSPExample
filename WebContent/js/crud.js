/*
	Author : Ali Can Kustemur
	File   : script/crud.js
	Date   : 29.06.2015
	P.Name : Login Page

*/
function deleteUser(userId,userName){
		if(confirm(userName+" adlı kullanıcı silinecektir,Onaylıyor musunuz ?")){
			document.getElementById("frm").action = "controller?crud=delete&userid="+userId;
			document.frm.submit();
		}
}
function updateUser(userId,userName,userAuthority){
			document.getElementById("frm").action ="controller?crud=update&userid="+userId+"&username="+userName+"&userauthority="+userAuthority;
			document.frm.submit();
}