import React from 'react'
import moment from 'moment';
import BookmarkAddedIcon from '@mui/icons-material/BookmarkAdded';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';

export const CustomEvent = (event) => {
    const bookedIcon = event.event.disponible ? <BookmarkBorderIcon /> :<BookmarkAddedIcon />;
    return (
        <div className="row">
            <div className="col">{moment(event.event.start).format('HH:mm')}</div>
            <div className="col">{event.event.modalidad}</div>
            <div className="col pull-right">
                {bookedIcon}
            </div>
        </div>
    )
}
