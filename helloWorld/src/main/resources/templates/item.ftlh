<style>
.title {
background-color:grey;
color:white;
}
th {
background-color:silver;
}
</style>

<form method=post action="/item/submit">
<table border=0>
<tr><td colspan=3 align=center class="title"><b>Item</b></td></tr>

<tr>
	<th>id</th>
	<th>title</th>
	<th>action</th>
</tr>

 
<tr>
	<td>${item.id}</td>
	<td><input type=text name="title" value="${item.title}"></td>
	<td><input type=submit value="Submit"></td>
</tr>


</table>

<input type=hidden name="id" value="${item.id}">
</form>

<!-- if itemz??>
<tr>
	<td></td>
	<td>none</td>
	<td></td>
</tr>
<else>
hi
</if -->