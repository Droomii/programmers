const lang = ['java', 'python', 'cpp'];
const type = ['backend', 'frontend'];
const career = ['junior', 'senior'];
const food = ['pizza', 'chicken'];

const columns = [lang, type, career, food];

function reduceFunc(acc, v, i, arr) {
    if (!arr[i+1]) {
        return {...acc, [v]: []};
    }
    return {...acc, [v]: arr[i]}
}

columns.reduce((acc, v, i) => {
    if ()
    
}, {})

function solution(info, query) {
    const db = lang.reduce((acc, v) => (
        {...acc, [v]: type.reduce((acc, v) => (
            {...acc, [v]: career.reduce((acc, v) => (
                {...acc, [v]: food.reduce((acc, v) => (
                    {...acc, [v]: []}), {})}), {})}), {})}), {})
    
    info.forEach(v => {
        const split = v.split(' ');
        const arr = db[split[0]][split[1]][split[2]][split[3]];
        arr.push(parseInt(split[4]));
        arr.sort((a, b) => a - b);
    });
    
    const answer = [];
    
    query.forEach(v => {
        const split = v.split(' ').filter(v => v !== 'and').map(v => {
            if (v === '-') {
                return []
            }
        });
        split[4] = parseInt(split[4]);
        
        const count = split.reduce((acc, v, i) => {
            let next = tree[v];
            if (!next){
                
            }
            
            return acc + next
        }, 0)
        
    });
    
    return answer;
}