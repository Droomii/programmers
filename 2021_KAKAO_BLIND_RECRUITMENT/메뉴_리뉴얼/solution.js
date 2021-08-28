function solution(orders, course) {
    orders = orders.map(v => v.split("").sort());
    const courseCount = orders.reduce((acc, order) => {
        combination(order, course).forEach(v => {
            acc[v] = (acc[v] || 0) + 1;
        });
        return acc;
    }, {});

    const answer = [];
    let length = 0;
    let max = 0;

    Object.entries(courseCount).sort((a, b) => {
        if (a[0].length === b[0].length) {
            return b[1] - a[1];
        }
        return a[0].length - b[0].length;
    }).forEach(v => {
        if (v[0].length > length) {
            length = v[0].length;
            max = v[1];
        }
        if (max > 1 && v[1] === max) answer.push(v[0]);
    })

    return answer.sort();
}

function combination(order, nums, combo, currentStr) {
    combo = combo || [];
    currentStr = currentStr || "";
    if (nums.indexOf(currentStr.length) > -1) {
        combo.push(currentStr);
    }
    for (let i = 0; i < order.length; i++) {
        combination(order.slice(i+1), nums, combo, currentStr + order[i]);
    }
    return combo;
}