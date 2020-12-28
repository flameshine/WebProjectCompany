function selected(option) {

    const label = option.value;

    if (label === 2) {
        document.getElementById("orderPrice").style.display = 'block';
        document.getElementById("rejectionReason").style.display = 'none';
    } else if (label === 5) {
        document.getElementById("orderPrice").style.display = 'none';
        document.getElementById("rejectionReason").style.display = 'block';
    }
}