function adjustBoardHeight() {
    let boards = document.querySelectorAll('.board');

    for (let i = 0; i < boards.length; i++) {
        let board = boards[i];
        let issues = board.querySelectorAll('.issue');
        let totalHeight = 0;
        let (let j = 0; j < issues.length; j++) {
            totalHeight += issues[j].offsetHeight;
        }
        totalHeight = totalHeight + 100;
        board.style.height = totalHeight + 'px';
    }
}

// 페이지 로드 시 보드 높이 조정
window.addEventListener('load', adjustBoardHeight);

// 윈도우 크기 변경 시 보드 높이 다시 조정
window.addEventListener('resize', adjustBoardHeight);